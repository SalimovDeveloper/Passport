package uz.salimovdeveloper.passport.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.salimovdeveloper.passport.databinding.ItemRecyclBinding
import uz.salimovdeveloper.passport.models.User

class RvAdapter (var list: List<User> = emptyList(), val rvClick: RvClick) : RecyclerView.Adapter<RvAdapter.Vh>() {
    inner class Vh(val itemRecyclBinding: ItemRecyclBinding): RecyclerView.ViewHolder(itemRecyclBinding.root){
        fun onBind(user: User){
            itemRecyclBinding.itemName.text = user.name
            itemRecyclBinding.itemSurname.text = user.surname
            itemRecyclBinding.itemImage.setImageURI(Uri.parse(user.imagePath))
            itemRecyclBinding.linerItem.setOnClickListener {
                rvClick.itemClick(user, itemRecyclBinding.linerItem)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemRecyclBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}