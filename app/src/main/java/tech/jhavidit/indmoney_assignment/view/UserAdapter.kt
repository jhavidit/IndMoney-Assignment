package tech.jhavidit.indmoney_assignment.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import tech.jhavidit.indmoney_assignment.databinding.UserItemListBinding
import tech.jhavidit.indmoney_assignment.model.UserResponse


class UserAdapter :
    RecyclerView.Adapter<UserAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: UserItemListBinding) : RecyclerView.ViewHolder(binding.root)

    private var userList = emptyList<UserResponse>()

    fun setUserList(userList: List<UserResponse>) {
        val diffCallBack = MyDiffCallBack(this.userList, userList)
        val diffResult = DiffUtil.calculateDiff(diffCallBack)
        diffResult.dispatchUpdatesTo(this)
        this.userList = userList
    }

    class MyDiffCallBack(
        private val oldList: List<UserResponse>,
        private val newList: List<UserResponse>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }
    }


    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(userList[position]) {
                binding.userName.text = this.name
                binding.userContact.text = this.phoneNumber.toString()
                binding.userDesignation.text = this.title
                Glide.with(holder.itemView.context)
                    .load(this.imageUrl)
                    .into(binding.userImage)
                binding.userCard.setOnClickListener {
                    holder.itemView.findNavController()
                        .navigate(HomeFragmentDirections.openDetails(this))
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            UserItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

}