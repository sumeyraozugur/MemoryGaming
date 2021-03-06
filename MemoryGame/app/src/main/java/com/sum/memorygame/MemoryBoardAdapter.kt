package com.sum.memorygame


import android.app.ActionBar
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.core.view.setMargins
import androidx.recyclerview.widget.RecyclerView
import com.sum.memorygame.model.BoardSize
import com.sum.memorygame.model.MemoryCard
import kotlin.math.max
import kotlin.math.min


class MemoryBoardAdapter(
    private val context: Context,
    private val boardSize: BoardSize,
    private val cards: List<MemoryCard>,
    private val cardClickListener: CardClickListener

) :

    RecyclerView.Adapter<MemoryBoardAdapter.ViewHolder>() {

    companion object {
        private const val MARGIN_SIZE = 10
        private const val TAG = "MemoryBoardAdapter"
    }

    interface  CardClickListener{
        fun onCardClicked(position:Int)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cardWith = parent.width /boardSize.getWidth() -(2* MARGIN_SIZE)//-20
        val cardHeight= parent.height /boardSize.getHeight() -(2* MARGIN_SIZE) // -40  -20
        val cardSideLength  = min(cardWith,cardHeight)
        val view :View =LayoutInflater.from(context).inflate(R.layout.memory_card,parent,false)
        val layoutParams = view.findViewById<CardView>(R.id.cardView).layoutParams as ViewGroup.MarginLayoutParams
        layoutParams.width = cardSideLength // -40
        layoutParams.height = cardSideLength // -40
        layoutParams.setMargins(MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE, MARGIN_SIZE)

        //Log.i(TAG, "Clicked on position ${}")


        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount() = boardSize.numCards

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val imageButton = itemView.findViewById<ImageButton>(R.id.imageButton)

        fun bind(position:Int){
            imageButton.setImageResource(if (cards[position].isFaceUp) cards[position].identifier else R.drawable.ic_launcher_background)
            imageButton.setOnClickListener {
                Log.i(TAG, "Clicked on position $position")
                cardClickListener.onCardClicked(position)




            }



        }

    }



}