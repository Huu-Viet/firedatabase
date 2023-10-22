package com.example.firedatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//    private DatabaseReference mData;
//    private DatabaseReference myRef;
    Button button;
    EditText editText;
    ListView listView;
    ArrayList<String> arrayList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        editText = findViewById(R.id.editTextText);
        listView = findViewById(R.id.listView);
        arrayList = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("địt mẹ mày cái củ loz hành bố hôm qua tới giờ");
//         mData = FirebaseDatabase.getInstance().getReference();
//         mData.child("hoten").setValue("ABCXYz");
        // use object
//        User user = new User("nguyen van a", "abc@gmail.com");
        DatabaseReference mData = database.getReference();
//        mData.child("user").setValue(user);
//        // use map
//        Map<String, Integer>myMap = new HashMap<>();
//        myMap.put("Xe oto", 4);
//        mData.child("phuong tien").setValue(myMap);
//        // use push
//        User user1 = new User("Nguyen Van c", "nguyenvanc@gmail.com");
//        mData.child("new user").push().setValue(user1);
        // bắt sự kiện hoàn thành sau khi setvalue
//        mData.child("Kiem tra su kien").setValue("đây là data", new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
//                if(error == null){
//                    Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(MainActivity.this, "Lưu thất bại", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mData.child("chat").push().setValue(editText.getText().toString(), new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null){
                            Toast.makeText(MainActivity.this, "đã gửi", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "gửi thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                editText.setText("");

            }
        });
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(adapter);
        mData.child("chat").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                arrayList.add(snapshot.getValue().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}