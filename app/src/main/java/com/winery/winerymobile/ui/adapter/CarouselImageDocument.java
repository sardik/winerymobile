package com.winery.winerymobile.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.projection.MediaProjectionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.winery.winerymobile.R;
import com.winery.winerymobile.ui.helper.RotateTransformation;
import com.winery.winerymobile.ui.helper.Utils;
import com.winery.winerymobile.ui.model.ImageDocumentDataCustomer;

import java.util.List;

public class CarouselImageDocument extends PagerAdapter {

    private List<ImageDocumentDataCustomer> models;
    private LayoutInflater layoutInflater;
    private Context context;
    MediaProjectionManager projectMedia;

    public CarouselImageDocument(List<ImageDocumentDataCustomer> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.adapter_pager_item_carousel, container, false);

        ImageView imageView;
        TextView title, desc;

        imageView = view.findViewById(R.id.image);
//        title = view.findViewById(R.id.title);
//        desc = view.findViewById(R.id.desc);

//        imageView.animate().rotation(270).start();

//        imageView.setImageResource(models.get(position).getImageID());
        Glide.with(context).
                asBitmap().
                load(models.get(position).getImage())
                .placeholder(R.color.colorWhite)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .fitCenter()
//                .transition(DrawableTransitionOptions.withCrossFade(100))
                .into(new CustomTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        imageView.setImageBitmap(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {
                    }
                });

//                Picasso.get()
//                .load(models.get(position).getImage()) // web image url
//              //if you want to rotate by 90 degrees
//                .error(R.color.colorWhite)
//                .placeholder(R.color.colorWhite)
//                .into(imageView);

//                Picasso.get()
//                .load(models.get(position).getImage())
//                .into(new Target() {
//                    @Override
//                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//
//                        imageView.setImageBitmap(bitmap);
//                    }
//
//                    @Override
//                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
//
//                    }
//
//                    @Override
//                    public void onPrepareLoad(Drawable placeHolderDrawable) {
//
//                    }
//                });


//        title.setText(models.get(position).getTitle());
//        desc.setText(models.get(position).getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, DetailActivity.class);
//                intent.putExtra("param", models.get(position).getTitle());
//                context.startActivity(intent);
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }


}
