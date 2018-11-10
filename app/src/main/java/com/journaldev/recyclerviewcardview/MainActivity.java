package com.journaldev.recyclerviewcardview;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<DataModel> data;
    static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    private TextView helloString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myBtn = (Button) findViewById(R.id.myBtn);
        myBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SecondActivity.class);

                String variableString = "1";
                boolean myBool = true;

                intent.putExtra("myvar", variableString);
                intent.putExtra("mybool", myBool);

                startActivity(intent);
            }
        });


        helloString = (TextView) findViewById(R.id.myTxt);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        data = new ArrayList<DataModel>();
        for (int i = 0; i < MyData.nameArray.length; i++) {
            data.add(new DataModel(
                    MyData.nameArray[i],
                    MyData.versionArray[i],
                    MyData.id_[i],
                    MyData.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

        adapter = new CustomAdapter(data);
        recyclerView.setAdapter(adapter);

        MyAsyncCall myAsyncCall = new MyAsyncCall();
        myAsyncCall.execute("a", "b");

    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {
            int selectedItemPosition = recyclerView.getChildPosition(v);
            RecyclerView.ViewHolder viewHolder
                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
            TextView textViewName
                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
            String selectedName = (String) textViewName.getText();
            int selectedItemId = -1;
            for (int i = 0; i < MyData.nameArray.length; i++) {
                if (selectedName.equals(MyData.nameArray[i])) {
                    selectedItemId = MyData.id_[i];
                }
            }
            removedItems.add(selectedItemId);
            data.remove(selectedItemPosition);
            adapter.notifyItemRemoved(selectedItemPosition);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("FIRST_ACTIVITY", "on stop");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("FIRST_ACTIVITY", "onDestroy");

    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("FIRST_ACTIVITY", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("FIRST_ACTIVITY", "onPause");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.add_item) {
            //check if any items to add
            if (removedItems.size() != 0) {
                addRemovedItemToList();
            } else {
                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
            }
        }
        return true;
    }

    private void addRemovedItemToList() {
        int addItemAtListPosition = 3;
        data.add(addItemAtListPosition, new DataModel(
                MyData.nameArray[removedItems.get(0)],
                MyData.versionArray[removedItems.get(0)],
                MyData.id_[removedItems.get(0)],
                MyData.drawableArray[removedItems.get(0)]
        ));
        adapter.notifyItemInserted(addItemAtListPosition);
        removedItems.remove(0);
    }


    class MyAsyncCall extends AsyncTask<String, Void, Void> {

        private String first, second;

        @Override
        protected void onPreExecute() {

            // Toast.makeText(getApplicationContext(), "pre execute", Toast.LENGTH_SHORT).show();

            helloString.setText("var in pre: " + first + ", " + second);

            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            // Toast.makeText(getApplicationContext(), "params are: "+first+", "+second, Toast.LENGTH_SHORT).show();

            helloString.setText("var in post: " + first + ", " + second);

            super.onPostExecute(aVoid);
        }

        @Override
        protected Void doInBackground(String... params) {

            try {

                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("http://www.google.com")
                        .build();

                Response response = client.newCall(request).execute();
                response.body().string();


                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }


            first = params[0];
            second = params[1];


            return null;
        }
    }

}