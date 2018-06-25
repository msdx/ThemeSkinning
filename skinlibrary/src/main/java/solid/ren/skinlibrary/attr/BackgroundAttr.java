package solid.ren.skinlibrary.attr;

import android.graphics.drawable.Drawable;
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
            Drawable bg = SkinResourcesUtils.getDrawable(attrValueRefId);
            ViewCompat.setBackground(view, bg);
        }
    }

    @Override
    protected void applyNightMode(View view) {
        if (isColor()) {
            view.setBackgroundColor(SkinResourcesUtils.getNightColor(attrValueRefId));
        } else if (isDrawable()) {
            ViewCompat.setBackground(view, SkinResourcesUtils.getNightDrawable(attrValueRefName));
        }
    }
}
