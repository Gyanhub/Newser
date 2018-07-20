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
import android.widget.Toast;


@SuppressLint("ValidFragment")
public class CrimeFragment extends Fragment {

    RecyclerView recyclerView;
    Context context;
    ProgressBar progressBar;
    Button btn;
    boolean result;
    MyVolley volley;
    String url = "https://newsapi.org/v2/everything?domains=domains=abplive.in,newsnation.in,india.com,intoday.in,ndtv.com&apiKey=c143ac55e9584316a9b5012075104807";

    CrimeFragment() {
    }

    CrimeFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_crime, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_crime);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        //  mTrending.setNestedScrollingEnabled(false);

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
