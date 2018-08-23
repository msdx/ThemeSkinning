package solid.ren.skinlibrary.attr;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.view.View;

import solid.ren.skinlibrary.attr.base.SkinAttr;
import solid.ren.skinlibrary.utils.SkinResourcesUtils;

/**
 * @author _SOLID
 * @author Geek_Soledad
 * @since 2016/4/13 21:46
 */
public class BackgroundAttr extends SkinAttr {

    @Override
    protected void applySkin(View view) {
        if (isColor()) {
            int color = SkinResourcesUtils.getColor(attrValueRefId);
            view.setBackgroundColor(color);
        } else if (isDrawable()) {
            setBackground(view, SkinResourcesUtils.getDrawable(attrValueRefId));
        }
    }

    @Override
    protected void applyNightMode(View view) {
        if (isColor()) {
            view.setBackgroundColor(SkinResourcesUtils.getNightColor(attrValueRefId));
        } else if (isDrawable()) {
            setBackground(view, SkinResourcesUtils.getNightDrawable(attrValueRefName));
        }
    }

    private void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ViewCompat.setBackground(view, drawable);
        } else {
            int start = ViewCompat.getPaddingStart(view);
            int top = view.getPaddingTop();
            int end = ViewCompat.getPaddingEnd(view);
            int bottom = view.getPaddingBottom();
            ViewCompat.setBackground(view, drawable);
            ViewCompat.setPaddingRelative(view, start, top, end, bottom);
        }
    }
}
