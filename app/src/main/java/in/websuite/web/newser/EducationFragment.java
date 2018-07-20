package in.websuite.web.newser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;


public class EducationFragment extends Fragment {


    RecyclerView recyclerView;
    String url="https://newsapi.org/v2/everything?domains=domains=abplive.in,newsnation.in,india.com,intoday.in,ndtv.com&q=education&sortBy=popularity&apiKey=c143ac55e9584316a9b5012075104807";
    Context context;
    ProgressBar progressBar;
    Button btn;
    MyVolley volley;
    boolean result;

    @SuppressLint("ValidFragment")
    EducationFragment()
    {}
    @SuppressLint("ValidFragment")
    EducationFragment(Context context)
    {
        this.context=context;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_education, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_education);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        progressBar =(ProgressBar) view.findViewById(R.id.progress);
        progressBar.setVisibility(View.VISIBLE);

        btn = (Button) view.findViewById(R.id.btn_try);
        result = false;

        volley = new MyVolley(context, recyclerView, url,btn,progressBar);



        result = volley.getVolleyData();


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = volley.getVolleyData();

            }
        });
        return view;
    }

}
