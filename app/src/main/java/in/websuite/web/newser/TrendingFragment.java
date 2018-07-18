package in.websuite.web.newser;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class TrendingFragment extends Fragment {

    RecyclerView mTrending;

    Context context;
    String url="https://newsapi.org/v2/top-headlines?country=in&apiKey=c143ac55e9584316a9b5012075104807";

    public TrendingFragment()
    {

    }
    @SuppressLint("ValidFragment")
    public TrendingFragment(Context context) {
        this.mTrending = mTrending;
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);
        mTrending = (RecyclerView) view.findViewById(R.id.rv_trend);
        mTrending.setLayoutManager(new LinearLayoutManager(context));
      //  mTrending.setNestedScrollingEnabled(false);

        MyVolley volley = new MyVolley(context, mTrending,url);
        volley.getVolleyData();
        // Inflate the layout for this fragment
        return view;
    }

}
