package rtbms.terxlabs.com;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.ConnectionService;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

public class NewsFeed extends AppCompatActivity {
    private FirebaseRecyclerAdapter<info_news, NewsFeed.NewsViewHolder> adapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newsfeed_recycleview );
        this.setRequestedOrientation(SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setBackgroundDrawable( new ColorDrawable( Color.parseColor( "#DD2C00" ) ) );
actionbar.setTitle( "Newsfeed" );
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("web").child( "newsfeed" );
        mDatabase.keepSynced(true);
        RecyclerView obj_newsrecycle = findViewById( R.id.newsrecycle );
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("web").child("newsfeed");
        Query personsQuery = db.orderByKey();

        obj_newsrecycle.hasFixedSize();
        obj_newsrecycle.setNestedScrollingEnabled(true);
        obj_newsrecycle.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        final ProgressDialog Dialog = new ProgressDialog(NewsFeed.this);

        Dialog.setMessage("Searching News");
        Dialog.show();
        FirebaseRecyclerOptions<info_news> personsOptions = new FirebaseRecyclerOptions.Builder<info_news>().setQuery(personsQuery, info_news.class).build();

        adapter = new FirebaseRecyclerAdapter<info_news, NewsFeed.NewsViewHolder>(personsOptions) {
            @Override
            protected void onBindViewHolder(@NonNull NewsViewHolder holder, int position, @NonNull info_news model) {
                holder.setDate(model.getDate());
                Dialog.hide();
                holder.setDescription(model.getDescription());
                holder.setImage(getBaseContext(), model.getImage());
                holder.setHead(model.getHead());
            }

            @NonNull
            @Override
            public NewsFeed.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.newsfeed, parent, false);

                return new NewsFeed.NewsViewHolder(view);
            }
        };

        obj_newsrecycle.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();


    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setDescription(String title){
            TextView post_title = mView.findViewById(R.id.news_description);
            post_title.setText(title);
        }
        public void setDate(String desc){
            TextView post_desc = mView.findViewById(R.id.news_date);
            post_desc.setText(desc);
        }
        public void setHead(String date){
            TextView head = mView.findViewById(R.id.news_name);
            head.setText(date);
        }
        public void setImage(Context ctx, String image){
            ImageView post_image =  mView.findViewById(R.id.news_image);
            Picasso.with(ctx).load(image).into(post_image);
        }
    }

}