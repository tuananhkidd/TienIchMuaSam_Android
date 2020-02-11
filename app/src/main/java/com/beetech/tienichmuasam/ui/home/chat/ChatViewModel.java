package com.beetech.tienichmuasam.ui.home.chat;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.beetech.tienichmuasam.BaseApplication;
import com.beetech.tienichmuasam.base.ListLoadmoreReponse;
import com.beetech.tienichmuasam.base.ListResponseBody;
import com.beetech.tienichmuasam.entity.chat.BaseMessage;
import com.beetech.tienichmuasam.entity.chat.ChatRoomInfo;
import com.beetech.tienichmuasam.entity.chat.ImageMessage;
import com.beetech.tienichmuasam.entity.chat.LastMessageWrapper;
import com.beetech.tienichmuasam.entity.chat.TextMessage;
import com.beetech.tienichmuasam.entity.chat.TypingState;
import com.beetech.tienichmuasam.entity.chat.UserInfo;
import com.beetech.tienichmuasam.entity.chat.VisitState;
import com.beetech.tienichmuasam.utils.Constant;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

public class ChatViewModel extends ViewModel {

    private boolean hasUploadingTask = false;
    private boolean isInRoom = false;
    private UserInfo userInfo;
    private MutableLiveData<ListLoadmoreReponse<BaseMessage>> listMessage = new MutableLiveData<>();
    private MutableLiveData<Map<String, Object>> messageListener = new MutableLiveData<>();
    private MutableLiveData<Boolean> typingListener = new MutableLiveData<>();
    private BaseMessage lastMessage = null;

    private ListenerRegistration messageChangeListenerRegistration;
    private ListenerRegistration typingStateChangeListenerRegistration;
    private ListenerRegistration userStateChangeListenerRegistration;
    private ListenerRegistration userVisitStateChangeListenerRegistration;
    private boolean lastMessageFetched = false;
    private String roomID = null;

    public MutableLiveData<ListLoadmoreReponse<BaseMessage>> getListMessage() {
        return listMessage;
    }

    public MutableLiveData<Map<String, Object>> getMessageListener() {
        return messageListener;
    }

    public MutableLiveData<Boolean> getTypingListener() {
        return typingListener;
    }

    @Inject
    public ChatViewModel() {

    }

    public String getRoomID() {
        //fix user ID
//        Task<QuerySnapshot> task = FirebaseFirestore.getInstance()
//                .collection(Constant.CHAT_ROOMS_COLLECTION)
//                .whereEqualTo("usersInfo.cd6fc031-4a31-433d-a0b4-a5fc3b2fb528.id", "cd6fc031-4a31-433d-a0b4-a5fc3b2fb528")
//                .get();
//        task.addOnSuccessListener(documentSnapshots -> {
//            if (documentSnapshots.getDocuments().size() != 0) {
//                roomID = (documentSnapshots.getDocuments().get(0).getId());
//            } else {
//                roomID = (null);
//            }
//        });

        roomID = "DPCopCtkfPPK0PnkkCub";

        return roomID;
    }


    public void getMessages(boolean isRefresh, int pageSize) {
        roomID = getRoomID();
        if (roomID == null) {
            return;
        }
        listMessage.setValue(new ListLoadmoreReponse<BaseMessage>().loading());
        Query messageQuery = FirebaseFirestore.getInstance()
                .collection(Constant.CHAT_ROOMS_COLLECTION)
                .document(roomID)
                .collection(ChatRoomInfo.MESSAGES)
                .orderBy(BaseMessage.CREATED_DATE, Query.Direction.DESCENDING)
                .limit(pageSize);
        if (lastMessage != null) {
            messageQuery = messageQuery.startAfter(lastMessage.getCreatedDate());
        }

        messageQuery.get().addOnSuccessListener(documentSnapshots -> {
            List<DocumentSnapshot> documentSnapshotList = documentSnapshots.getDocuments();
            List<BaseMessage> baseMessages = new ArrayList<>(documentSnapshotList.size());
            for (DocumentSnapshot documentSnapshot : documentSnapshotList) {
                switch (documentSnapshot.getString(BaseMessage.TYPE)) {
                    case BaseMessage.TEXT_MESSAGE: {
                        baseMessages.add(documentSnapshot.toObject(TextMessage.class));
                    }
                    break;

                    case BaseMessage.IMAGE_MESSAGE: {
                        baseMessages.add(documentSnapshot.toObject(ImageMessage.class));
                    }
                    break;

                    default: {
                        break;
                    }
                }
            }
            int messageCount = baseMessages.size();
            if (messageCount > 0) {
                this.lastMessage = baseMessages.get(messageCount - 1);
            }
            listMessage.setValue(new ListLoadmoreReponse<BaseMessage>().success(new ListResponseBody<>(baseMessages), isRefresh, messageCount < pageSize));
        }).addOnFailureListener(e -> {
//            listener.onServerError(e.getMessage());
            listMessage.setValue(new ListLoadmoreReponse<BaseMessage>().error(e));
        });
    }

    public void enterRoom(String roomID, String userID) {
        //fixme
        roomID = getRoomID();
        userID = "cd6fc031-4a31-433d-a0b4-a5fc3b2fb528";
        FirebaseFirestore.getInstance().collection(Constant.CHAT_ROOMS_COLLECTION)
                .document(roomID)
                .collection(ChatRoomInfo.VISIT_STATES)
                .document(userID)
                .set(new VisitState(VisitState.IN_ROOM_STATE))
                .addOnSuccessListener(aVoid -> {
                    //change online state
                })
                .addOnFailureListener(e -> {
                    //handle error
                });
    }

    public void leftRoom(String roomID, String userID) {
        //fixme
        roomID = getRoomID();
        userID = "cd6fc031-4a31-433d-a0b4-a5fc3b2fb528";
        FirebaseFirestore.getInstance().collection(Constant.CHAT_ROOMS_COLLECTION)
                .document(roomID)
                .collection(ChatRoomInfo.VISIT_STATES)
                .document(userID)
                .set(new VisitState(VisitState.LEFT_ROOM_STATE))
                .addOnSuccessListener(aVoid -> {
                    //change online state

                })
                .addOnFailureListener(e -> {
                    //handle error

                });
    }

    //region message

    public void sendTextMessage(String message) {
        if (TextUtils.isEmpty(message)) {
            return;
        }
        if (hasUploadingTask) {
            Toast.makeText(BaseApplication.getContext(), "Đang tải ảnh lên", Toast.LENGTH_SHORT).show();
            return;
        }
        roomID = getRoomID();

        DocumentReference chatRoomRef = FirebaseFirestore.getInstance()
                .collection(Constant.CHAT_ROOMS_COLLECTION)
                .document(roomID);
        //fixme
        String userID = "cd6fc031-4a31-433d-a0b4-a5fc3b2fb528";

        TextMessage textMessage = new TextMessage(userID, message);

        WriteBatch writeBatch = FirebaseFirestore.getInstance().batch();
        writeBatch.set(chatRoomRef.collection(ChatRoomInfo.MESSAGES).document(), textMessage);
        writeBatch.set(chatRoomRef, new LastMessageWrapper(textMessage), SetOptions.merge());
        writeBatch.commit()
                .addOnSuccessListener(documentReference -> {
                    //push message notification to admin
                })
                .addOnFailureListener(error -> {
                    //handle error
                });
    }


    public void validateSentImageMessage(List<Uri> imageUris) {
        if (imageUris.isEmpty()) {
            return;
        }
        if (hasUploadingTask) {
            Toast.makeText(BaseApplication.getContext(), "Đang tải ảnh lên", Toast.LENGTH_SHORT).show();
            return;
        }
//        Intent intent = new Intent(context, SendImageMessageService.class);
//        intent.putExtra(Constants.KEY_IMAGE_FOLDER, roomID);
//        intent.putExtra(Constants.KEY_OWNER, (Parcelable) ownerInfo);
//        ArrayList<String> imageUrisString = new ArrayList<>(imageUris.size());
//        for (Uri uri : imageUris) {
//            imageUrisString.add(uri.toString());
//        }
//        intent.putStringArrayListExtra(Constants.KEY_IMAGE_URIS, imageUrisString);
//        context.startService(intent);
//        ImageMessage imageMessage = new ImageMessage(ownerInfo.getId(), imageUris);
//        chatFragmentView.addTopMessage(imageMessage);
    }


    public void registerOnMessageChangedListener(String roomID,
                                                 int pageSize) {
        roomID = getRoomID();

        Query messagesQuery = FirebaseFirestore.getInstance().collection(Constant.CHAT_ROOMS_COLLECTION)
                .document(roomID)
                .collection(ChatRoomInfo.MESSAGES)
                .orderBy(BaseMessage.CREATED_DATE, Query.Direction.DESCENDING)
                .limit(pageSize);

        messageChangeListenerRegistration = messagesQuery
                .addSnapshotListener((documentSnapshots, e) -> {
                    if (e != null) {
                        //handle error
                        return;
                    }

                    List<DocumentChange> documentChanges = documentSnapshots.getDocumentChanges();
                    int size = documentChanges.size();
                    if (!lastMessageFetched) {
                        TextMessage lastTextMessage = size == 0 ?
                                null : documentChanges.get(size - 1).getDocument().toObject(TextMessage.class);
                        lastMessage = lastTextMessage;
//                        listener.onLastElementFetched(lastTextMessage,
//                                lastTextMessage == null || size < pageSize);
                        lastMessageFetched = true;
                    }
                    for (int i = size - 1; i >= 0; i--) {
                        resolveMessageType(documentChanges.get(i));
                    }
                });
    }

    private void resolveMessageType(DocumentChange documentChange) {
        switch (documentChange.getDocument().getString(BaseMessage.TYPE)) {
            case BaseMessage.TEXT_MESSAGE: {
                resolveTextMessage(documentChange);
            }
            break;

            case BaseMessage.IMAGE_MESSAGE: {
                resolveImageMessage(documentChange);
            }
            break;

            default: {
                break;
            }
        }
    }

    private void resolveTextMessage(DocumentChange documentChange) {
        TextMessage textMessage = documentChange.getDocument().toObject(TextMessage.class);
        HashMap<String, Object> data = new HashMap<>();
        data.put(Constant.DATA_TYPE, BaseMessage.TEXT_MESSAGE);
        data.put(Constant.DATA, textMessage);

        switch (documentChange.getType()) {
            case ADDED: {
                data.put(Constant.ADD, true);
            }
            break;

            case REMOVED:
            case MODIFIED: {
                data.put(Constant.ADD, false);
                data.put(Constant.POSITION,documentChange.getOldIndex());
            }
            break;

            default: {
                break;
            }
        }
        messageListener.setValue(data);

    }

    private void resolveImageMessage(DocumentChange documentChange) {
        switch (documentChange.getType()) {
            case ADDED: {
                DocumentSnapshot documentSnapshot = documentChange.getDocument();
                if (!documentSnapshot.getMetadata().hasPendingWrites()) {
                    ImageMessage imageMessage = documentChange.getDocument().toObject(ImageMessage.class);
                    HashMap<String, Object> data = new HashMap<>();
                    data.put(Constant.ADD, true);
                    data.put(Constant.DATA_TYPE, BaseMessage.IMAGE_MESSAGE);
                    data.put(Constant.DATA, imageMessage);

                    messageListener.setValue(data);
                }
            }
            break;

            case MODIFIED: {
                ImageMessage imageMessage = documentChange.getDocument().toObject(ImageMessage.class);
                HashMap<String, Object> data = new HashMap<>();
                data.put(Constant.ADD, false);
                data.put(Constant.DATA_TYPE, BaseMessage.IMAGE_MESSAGE);
                data.put(Constant.DATA, imageMessage);
                data.put(Constant.POSITION,documentChange.getOldIndex());

                messageListener.setValue(data);
            }
            break;

            default: {
                break;
            }
        }
    }

    public void unregisterOnMessageChangedListener() {
        if (messageChangeListenerRegistration != null) {
            messageChangeListenerRegistration.remove();
        }
    }
    //endregion


    //region typing
    public void registerFriendTypingListener(String ignoreUserID) {
        roomID = getRoomID();
        typingStateChangeListenerRegistration = FirebaseFirestore.getInstance().collection(Constant.CHAT_ROOMS_COLLECTION)
                .document(roomID)
                .collection(ChatRoomInfo.TYPING_STATES)
                .addSnapshotListener((documentSnapshots, e) -> {
                    if (e != null) {
                        return;
                    }
                    List<DocumentChange> documentChanges = documentSnapshots.getDocumentChanges();
                    for (DocumentChange documentChange : documentChanges) {
                        switch (documentChange.getType()) {
                            case ADDED:

                            case MODIFIED: {
                                DocumentSnapshot documentSnapshot = documentChange.getDocument();
                                String userID = documentSnapshot.getId();
                                if (!userID.equals(ignoreUserID)) {
                                    typingListener.setValue(documentSnapshot.getBoolean(TypingState.IS_TYPING));
                                }
                            }
                            break;

                            default: {
                                break;
                            }
                        }
                    }
                });
    }

    public void unregisterFriendTypingListener() {
        if (typingStateChangeListenerRegistration != null) {
            typingStateChangeListenerRegistration.remove();
        }
    }

    public void updateUserTypingState(boolean state) {
        roomID = getRoomID();
        String userID = "cd6fc031-4a31-433d-a0b4-a5fc3b2fb528";
        FirebaseFirestore.getInstance().collection(Constant.CHAT_ROOMS_COLLECTION)
                .document(roomID)
                .collection(ChatRoomInfo.TYPING_STATES)
                .document(userID)
                .update(TypingState.IS_TYPING, state)
                .addOnSuccessListener(aVoid -> {

                })
                .addOnFailureListener(e -> {

                });
    }
    //endregion

}
