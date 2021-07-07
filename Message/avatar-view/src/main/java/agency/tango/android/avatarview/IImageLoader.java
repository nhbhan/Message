package agency.tango.android.avatarview;

import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import agency.tango.android.avatarview.views.AvatarView;

public interface IImageLoader {
    void loadImage(@NonNull AvatarView avatarView, @NonNull AvatarPlaceholder avatarPlaceholder, String avatarUrl);

    void loadImage(@NonNull AvatarView avatarView, String avatarUrl, String name);

    void loadImage(@NonNull AvatarView avatarView, String avatarUrl, String name, int textSizePercentage);

    void loadImage(@NonNull AvatarView avatarView, @NonNull String name, String avatarUrl, Drawable placeHolderDrawable);
}
