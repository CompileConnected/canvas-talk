package ca.adamerb.canvastalk

import android.graphics.Canvas
import android.graphics.Paint

class Slide8(override val view: SlideHolderView): Slide {

    val header = Header(view, "That's All!")

    val bullets = Bullets(
        view = view,
        bullets = listOf(
            "Adam Erb",
            "@erbal",
            "adam.l.erb@gmail.com",
            "github.com/aerb/canvas-talk"
        ),
        paint = Paint().apply {
            textSize = dp(15)
            color = White
            typeface = UbuntuBold
            isAntiAlias = true
        }
    )

    init {
        runAnimation { t -> header.lineAnimation = t }
        animateBullets()
    }

    fun animateBullets() {
        if(bullets.showNext()) {
            view.postDelayed({ animateBullets() }, 500)
        }
    }

    override fun onLayout(width: Int, height: Int) {
        header.layout(width)

        bullets.position.set(PaddingF, header.lineY + PaddingF)
        bullets.layout(width / 2 - Padding * 2)
    }

    private val alpha: Int = 255
    override fun onDraw(canvas: Canvas) {
        canvas.drawBackground(view.width, view.height, Shade2)

        canvas.saveLayerAlpha(0f, 0f, view.width.toFloat(), view.height.toFloat(), alpha, Canvas.ALL_SAVE_FLAG)

        header.draw(canvas)
        bullets.draw(canvas)


        canvas.restore()
    }

    override fun nextPressed() {

    }
}

