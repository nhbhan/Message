package agency.tango.android.avatarview;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import agency.tango.android.avatarview.utils.StringUtils;
import agency.tango.android.avatarview.views.AvatarView;


public class GlideLoader extends ImageLoaderBase {

    public GlideLoader() {
        super();
    }

    public GlideLoader(String defaultPlaceholderString) {
        super(defaultPlaceholderString);
    }

    @Override
    public void loadImage(@NonNull AvatarView avatarView, @NonNull AvatarPlaceholder avatarPlaceholder, String avatarUrl) {
        Glide.with(avatarView.getContext().getApplicationContext())
                .load(avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                        .placeholder(avatarPlaceholder).centerCrop()
                        )
                .into(avatarView);
    }

    @Override
    public void loadImage(@NonNull AvatarView avatarView, @NonNull String name, String avatarUrl, Drawable placeHolderDrawable) {
        Drawable placeHolder = StringUtils.isNullOrEmpty(name) ? placeHolderDrawable : new AvatarPlaceholder(name);

        Glide.with(avatarView.getContext().getApplicationContext())
                .load(avatarUrl)
                .transition(DrawableTransitionOptions.withCrossFade())
                .apply(new RequestOptions()
                        .placeholder(placeHolder).centerCrop())
                .into(avatarView);
    }

}
