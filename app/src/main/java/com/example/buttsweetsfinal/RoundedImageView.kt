package com.example.buttsweetsfinal

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.TypedValue


class RoundedTopImageView : androidx.appcompat.widget.AppCompatImageView {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private var mBitmap: Bitmap? = null
    private var mMatrix: Matrix? = null
    private val mRadius: Int = convertDpToPixel(10)
    private var mWidth = 0
    private var mHeight = 0
    private var mDrawable: Drawable? = null

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        mPaint = Paint()
        mPaint!!.color = Color.WHITE
        mPath = Path()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        mDrawable = drawable
        mBitmap = drawable?.let { drawableToBitmap(it) }
        val bDIWidth = mBitmap!!.width
        val bDIHeight = mBitmap!!.height

        //Fit to screen.
        val scale: Float
        scale = if (mHeight / bDIHeight.toFloat() >= mWidth / bDIWidth.toFloat()) {
            mHeight / bDIHeight.toFloat()
        } else {
            mWidth / bDIWidth.toFloat()
        }
        val borderLeft = (mWidth - bDIWidth * scale) / 2
        val borderTop = (mHeight - bDIHeight * scale) / 2
        mMatrix = imageMatrix
        val drawableRect = RectF(0F, 0F, bDIWidth.toFloat(), bDIHeight.toFloat())
        val viewRect = RectF(
            borderLeft,
            borderTop,
            bDIWidth * scale + borderLeft,
            bDIHeight * scale + borderTop
        )
        mMatrix?.setRectToRect(drawableRect, viewRect, Matrix.ScaleToFit.CENTER)
        invalidate()
    }

    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            if (drawable.bitmap != null) {
                return drawable.bitmap
            }
        }
        val bitmap: Bitmap = if (drawable.intrinsicWidth <= 0 || drawable.intrinsicHeight <= 0) {
            Bitmap.createBitmap(
                1,
                1,
                Bitmap.Config.ARGB_8888
            ) // Single color bitmap will be created of 1x1 pixel
        } else {
            Bitmap.createBitmap(
                drawable.intrinsicWidth,
                drawable.intrinsicHeight,
                Bitmap.Config.ARGB_8888
            )
        }
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        mWidth = MeasureSpec.getSize(widthMeasureSpec)
        mHeight = MeasureSpec.getSize(heightMeasureSpec)
        if (mDrawable != null && mHeight > 0 && mWidth > 0) {
            setImageDrawable(mDrawable!!)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (mBitmap == null) {
            return
        }
        canvas.drawColor(Color.TRANSPARENT)
        mPath!!.reset()
        mPath!!.moveTo(0f, mRadius.toFloat())
        mPath!!.lineTo(0f, height.toFloat())
        mPath!!.lineTo(width.toFloat(), height.toFloat())
        mPath!!.lineTo(width.toFloat(), mRadius.toFloat())
        mPath!!.quadTo(width.toFloat(), 0f, (width - mRadius).toFloat(), 0f)
        mPath!!.lineTo(mRadius.toFloat(), 0f)
        mPath!!.quadTo(0f, 0f, 0f, mRadius.toFloat())
        mPaint?.let { canvas.drawPath(mPath!!, it) }
        canvas.clipPath(mPath!!)
        mMatrix?.let { canvas.drawBitmap(mBitmap!!, it, mPaint) }
    }
    private fun convertDpToPixel(dp: Int): Int {
        val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp.toFloat(), displayMetrics)
            .toInt()
    }
}



