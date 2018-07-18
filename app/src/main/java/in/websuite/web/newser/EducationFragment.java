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


public class EducationFragment extends Fragment {


    RecyclerView recyclerView;
    String url="https://newsapi.org/v2/everything?domains=thehindu.com&q=education&sortBy=popularity&apiKey=c143ac55e9584316a9b5012075104807";
    Context context;

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
        recyclerView.setNestedScrollingEnabled(false);

        MyVolley volley = new MyVolley(context, recyclerView,url);
        volley.getVolleyData();
        return view;
    }

}
