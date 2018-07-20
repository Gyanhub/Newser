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

@SuppressLint("ValidFragment")
public class ScienceFragment extends Fragment {

    Context context;
    RecyclerView recyclerView;
    Button btn;
    ProgressBar progressBar;
    MyVolley volley;
    boolean result;
    String url="https://newsapi.org/v2/everything?domains=domains=abplive.in,newsnation.in,india.com&q=science&apiKey=c143ac55e9584316a9b5012075104807";

    public ScienceFragment()
    {}
    public ScienceFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_science, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_science);
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

        return  view;
    }

}
