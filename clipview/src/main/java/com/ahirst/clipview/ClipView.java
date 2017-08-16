package com.ahirst.clipview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * Created by adamhirst on 16/08/2017.
 */

// TODO: Add javadocs

public class ClipView extends FrameLayout {

    // Padding
    private int mPaddingTop;
    private int mPaddingRight;
    private int mPaddingBottom;
    private int mPaddingLeft;

    // Radius
    private int mRadiusTopLeft;
    private int mRadiusTopRight;
    private int mRadiusBottomLeft;
    private int mRadiusBottomRight;

    // Clip vertex
    private int mTopLeftVertX;
    private int mTopLeftVertY;
    private int mTopRightVertX;
    private int mTopRightVertY;
    private int mBottomLeftVertX;
    private int mBottomLeftVertY;
    private int mBottomRightVertX;
    private int mBottomRightVertY;

    public ClipView(Context context) {
        super(context);

        // Overridden onDraw method
        setWillNotDraw(false);
    }

    public ClipView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Overridden onDraw method
        setWillNotDraw(false);

        // Get the typed array of attributes
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ClipView,
                0, 0);

        try {
            // Padding
            int mPadding = a.getDimensionPixelSize(R.styleable.ClipView_clipPadding, 0);
            mPaddingLeft = a.getDimensionPixelSize(R.styleable.ClipView_clipPaddingLeft, mPadding);
            mPaddingTop = a.getDimensionPixelSize(R.styleable.ClipView_clipPaddingTop, mPadding);
            mPaddingRight = a.getDimensionPixelSize(R.styleable.ClipView_clipPaddingRight, mPadding);
            mPaddingBottom = a.getDimensionPixelSize(R.styleable.ClipView_clipPaddingBottom, mPadding);

            // Radius
            int mRadius = a.getDimensionPixelSize(R.styleable.ClipView_clipRadius, 0);
            mRadiusTopLeft = a.getDimensionPixelSize(R.styleable.ClipView_clipRadiusTopLeft, mRadius);
            mRadiusTopRight = a.getDimensionPixelSize(R.styleable.ClipView_clipRadiusTopRight, mRadius);
            mRadiusBottomLeft = a.getDimensionPixelSize(R.styleable.ClipView_clipRadiusBottomLeft, mRadius);
            mRadiusBottomRight = a.getDimensionPixelSize(R.styleable.ClipView_clipRadiusBottomRight, mRadius);

            // Vertex
            mTopLeftVertX = a.getDimensionPixelSize(R.styleable.ClipView_clipTopLeftVertX, 0);
            mTopLeftVertY = a.getDimensionPixelSize(R.styleable.ClipView_clipTopLeftVertY, 0);
            mTopRightVertX = a.getDimensionPixelSize(R.styleable.ClipView_clipTopRightVertX, 0);
            mTopRightVertY = a.getDimensionPixelSize(R.styleable.ClipView_clipTopRightVertY, 0);
            mBottomLeftVertX = a.getDimensionPixelSize(R.styleable.ClipView_clipBottomLeftVertX, 0);
            mBottomLeftVertY = a.getDimensionPixelSize(R.styleable.ClipView_clipBottomLeftVertY, 0);
            mBottomRightVertX = a.getDimensionPixelSize(R.styleable.ClipView_clipBottomRightVertX, 0);
            mBottomRightVertY = a.getDimensionPixelSize(R.styleable.ClipView_clipBottomRightVertY, 0);
        } finally {
            // Recycle the attributes when done
            a.recycle();
        }
    }

    public void setCipPadding(int padding) {
        setClipPadding(padding, padding, padding, padding);
    }

    public void setClipPadding(int left, int top, int right, int bottom) {
        mPaddingLeft = left;
        mPaddingTop = top;
        mPaddingRight = right;
        mPaddingBottom = right;

        invalidate();
    }

    public void setClipRadius(int radius) {
        setClipRadius(radius, radius, radius, radius);
    }

    public void setClipRadius(int topLeft, int topRight, int bottomRight, int bottomLeft) {
        mRadiusTopLeft = topLeft;
        mRadiusTopRight = topRight;
        mRadiusBottomRight = bottomRight;
        mRadiusBottomLeft = bottomLeft;

        invalidate();
    }

    public void setVertex(int topLeftX, int topLeftY, int topRightX, int topRightY, int bottomLeftX, int bottomLeftY, int bottomRightX, int bottomRightY) {
        if (topLeftX != -1) mTopLeftVertX = topLeftX;
        if (topLeftY != -1) mTopLeftVertY = topLeftY;
        if (topRightX != -1) mTopRightVertX = topRightX;
        if (topRightY != -1) mTopRightVertY = topRightY;
        if (bottomLeftX != -1) mBottomLeftVertX = bottomLeftX;
        if (bottomLeftY != -1) mBottomLeftVertY = bottomLeftY;
        if (bottomRightX != -1) mBottomRightVertX = bottomRightX;
        if (bottomRightY != -1) mBottomRightVertY = bottomRightY;

        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        // Get the width and height
        int w = getWidth();
        int h = getHeight();

        // Clip for padding
        if (mPaddingTop != 0 || mPaddingLeft != 0 || mPaddingRight != 0 || mPaddingBottom != 0) {
            canvas.clipRect(mPaddingLeft, mPaddingTop, w - mPaddingRight, h - mPaddingBottom);
        }

        // Clip for radius
        if (mRadiusTopLeft != 0 || mRadiusTopRight != 0 || mRadiusBottomLeft != 0 || mRadiusBottomRight != 0) {
            Path path = new Path();

            float[] radii = { mRadiusTopLeft, mRadiusTopLeft,
                    mRadiusTopRight, mRadiusTopRight,
                    mRadiusBottomRight, mRadiusBottomRight,
                    mRadiusBottomLeft, mRadiusBottomLeft};

            path.addRoundRect(mPaddingLeft, mPaddingTop, w - mPaddingRight, h - mPaddingBottom, radii, Path.Direction.CW);

            canvas.clipPath(path);
        }

        // Clip vertex
        if (mTopLeftVertX != 0 || mTopLeftVertY != 0 || mTopRightVertX != 0 || mTopRightVertY != 0
                || mBottomLeftVertX != 0 || mBottomLeftVertY != 0 || mTopRightVertX != 0 || mBottomRightVertY != 0) {
            Path path = new Path();

            path.moveTo(mTopLeftVertX, mTopLeftVertY);
            path.lineTo(w - mTopRightVertX, mTopRightVertY);
            path.lineTo(w - mBottomRightVertX, h - mBottomRightVertY);
            path.lineTo(mBottomLeftVertX, h - mBottomLeftVertY);
            path.close();

            canvas.clipPath(path);
        }
    }

}
