package in.websuite.web.newser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsDetail extends AppCompatActivity {

    ImageView mImage;
    TextView mTitle,mDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        mImage=(ImageView) findViewById(R.id.news_img);
        mTitle=(TextView)findViewById(R.id.txt_title);
        mDesc=(TextView)findViewById(R.id.txt_details);
        getIntentDetails();
    }
    protected void getIntentDetails()
    {
        if(getIntent().hasExtra("image_url") && getIntent().hasExtra("news_details"))
        {
            String image_url=getIntent().getStringExtra("image_url");
            String news_title=getIntent().getStringExtra("news_title");
            String news_details=getIntent().getStringExtra("news_details");

            Picasso.get().load(image_url).placeholder(R.drawable.loading).error(R.drawable.loading).into(mImage);
            mTitle.setText(news_title);
            mDesc.setText(news_details);
        }
    }
}
