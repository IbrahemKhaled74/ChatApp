package com.example.chat_app.dataBase

import com.example.chat_app.home.Room
import com.example.chat_app.join_room.JoinRoom
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

fun getCollection(colle:String):CollectionReference{
    val fs=Firebase.firestore
    return fs.collection(colle)

}
fun getRoomDoc(roomId: String?): DocumentReference {
    val db = Firebase.firestore
    return db.collection(Room.COLLECTION_NAME)
        .document(roomId!!)

}
fun roomRef(roomId:String): CollectionReference {
    val collectionRef = getCollection(Room.COLLECTION_NAME)
    val roomRef= collectionRef.document(roomId)
    return roomRef.collection(Message.COLLECTION_NAME)

}
fun joinRoomRef(room: Room): DocumentReference {
    val collectionRef = getCollection(Room.COLLECTION_NAME)
    return collectionRef.document(room.id!!)
}

fun addAccountToFireStore(user:AppUser,onSuccessListener: OnSuccessListener<Void>,
onFailureListener: OnFailureListener){
    getCollection(AppUser.COLLECTION_NAME)
        .document(user.id!!)
        .set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)


}
fun signInFireStore(userid: String?,onSuccessListener: OnSuccessListener<DocumentSnapshot>,
onFailureListener: OnFailureListener
                    ){
    getCollection(AppUser.COLLECTION_NAME)
        .document(userid!!)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}


fun JoinRooms(room: Room,user: JoinRoom?,onSuccessListener: OnSuccessListener<Void>,
             onFailureListener: OnFailureListener){
    val joinRoomRef= joinRoomRef(room)
    val joinRoomDoc=joinRoomRef.collection(JoinRoom.COLLECTION_NAME)
        .document(user?.userId!!)

    joinRoomDoc.set(user)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}

fun addRoomToFireStore(room: Room,onSuccessListener: OnSuccessListener<Void>,
                       onFailureListener: OnFailureListener){
    val coll= getCollection(Room.COLLECTION_NAME)
    val doc=coll.document()
    room.id=doc.id
    doc.set(room)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)
}

fun getRoomFromFireStore(onSuccessListener: OnSuccessListener<QuerySnapshot>,
                         onFailureListener: OnFailureListener){
    val db =Firebase.firestore
    db.collection(Room.COLLECTION_NAME)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}
fun getUserRoomRef(roomId: String?,userid: String?,onSuccessListener: OnSuccessListener<DocumentSnapshot>,
                   onFailureListener: OnFailureListener){
    val db =Firebase.firestore
    db.collection(Room.COLLECTION_NAME)
        .document(roomId!!)
        .collection(JoinRoom.COLLECTION_NAME)
        .document(userid!!)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}
fun removeUserFromFireStore(roomId: String?,userid: String?,onSuccessListener: OnSuccessListener<Void>,
                            onFailureListener: OnFailureListener){
    getRoomDoc(roomId).collection(JoinRoom.COLLECTION_NAME)
        .document(userid!!)
        .delete()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}
fun updateNumber(roomId: String?,member:Int,onSuccessListener: OnSuccessListener<Void>,
               onFailureListener: OnFailureListener){
    getRoomDoc(roomId).update( JoinRoom.MEMBER_NUMBER,member)
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}
fun knowNumber(roomId: String?,onSuccessListener: OnSuccessListener<QuerySnapshot>,
                 onFailureListener: OnFailureListener){
    getRoomDoc(roomId).collection(JoinRoom.COLLECTION_NAME)
        .get()
        .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)


}
fun addMessageToFireStore( message: Message,onSuccessListener: OnSuccessListener<Void>,
                          onFailureListener: OnFailureListener){
    val messageRef= roomRef(message.roomId!!)
    val messageDoc= messageRef.document()
    message.id=messageDoc.id
    messageDoc.set(message)
     .addOnSuccessListener(onSuccessListener)
        .addOnFailureListener(onFailureListener)

}

