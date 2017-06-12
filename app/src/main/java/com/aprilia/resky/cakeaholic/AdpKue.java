package com.aprilia.resky.cakeaholic;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aprilia.resky.cakeaholic.classes.ClassUtil;

import java.util.List;

/**
 * Created by limakali on 5/29/2017.
 */

public class AdpKue extends ArrayAdapter<ItemKue>
{

    public AdpKue(@NonNull Context context, @LayoutRes int resource, @NonNull List<ItemKue> objects)
    {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View v = convertView;

        if (v == null)
            v = LayoutInflater.from(getContext()).inflate(R.layout.item_kue, parent, false);

        ImageView imgKue = (ImageView) v.findViewById(R.id.imgKue);
        TextView txtKue = (TextView) v.findViewById(R.id.txtNama);

        ItemKue i = getItem(position);

        Bitmap b = ClassUtil.getBitmapFromAsset(getContext(), i.getGambar());
        Bitmap resized = getResizedBitmap(b, 150, 200);
        b.recycle();

        imgKue.setImageBitmap(resized);
        txtKue.setText(i.getNamaKue());

        return v;
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        // create a matrix for the manipulation
        Matrix matrix = new Matrix();

        // resize the bit map
        matrix.postScale(scaleWidth, scaleHeight);

        // recreate the new Bitmap
        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);

        return resizedBitmap;
    }
}
