package com.bk.t5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StudentListViewActivity extends AppCompatActivity {
    private OkHttpClient client = new OkHttpClient();
    private RecyclerView recyclerView;
    private ProgressBar prgBar;
    private ImageView ivPic;
    private ArrayList<Student> arrData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        populateArrayList();
    }

    private void findViews(){
        recyclerView = findViewById(R.id.View);
        prgBar = findViewById(R.id.PrgBar);
    }

    private void populateArrayList(){
        prgBar.setVisibility(View.VISIBLE);

        Request request = new Request.Builder()
                .url("https://api.jsonbin.io/b/5f2773c81823333f8f1afec3/1")
                .build();
        client.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                prgBar.setVisibility(View.GONE);
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String resString = response.body().string();

                try {
                    JSONObject resObject = new JSONObject(resString);

                    JSONArray nameArray = resObject.getJSONArray("response");

                        for (int i = 0; i < nameArray.length(); i++) {// looping through All data in array
                            JSONObject aObject = nameArray.getJSONObject(i);
                            Student student = new Student();

                            student.setName(aObject.getString("name"));
                            student.setCourse(aObject.getString("course"));
                            student.setMatric(aObject.getString("id"));
                            student.setPics(aObject.getString("photo"));

                            arrData.add(student);
                        }

                        runOnUiThread(() -> {
                            StudentAdapter adapter = new StudentAdapter(StudentListViewActivity.this, arrData);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(StudentListViewActivity.this));
                            prgBar.setVisibility(View.GONE);
                        }); }
                catch (Exception e) {
                        e.printStackTrace();
                }
            }
        });
    }
}