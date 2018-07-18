package in.websuite.web.newser;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class MyVolley {

    NewsApi newsApi;
    RecyclerView recyclerView;

    Context context;

    String NEWS_URL ;
    String newsType="";

    MyVolley(Context context,RecyclerView recyclerView,String url) {
        this.context=context;
        this.recyclerView=recyclerView;
        this.NEWS_URL=url;

    }

    void getVolleyData() {
        StringRequest request = new StringRequest(NEWS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                newsApi = gson.fromJson(response, NewsApi.class);
                recyclerView.setAdapter(new MyAdapter(context,newsApi));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Unable to fetch data : " + error, Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(request);
    }
}
