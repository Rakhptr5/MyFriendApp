package com.example.myfriendapp

import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MyFriendsFragment : Fragment() {
    private var fab_btn: FloatingActionButton? = null
    private var listMyFriends: RecyclerView? = null
    private lateinit var listTeman: MutableList<MyFriend>
    private var db: AppDatabase? = null
    private var myFriendDAO: MyFriendDAO? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.my_friends_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLocalDB()
        initView()

    }

    private fun initLocalDB() {
        db = AppDatabase.getAppDataBase(requireActivity())
        myFriendDAO = db?.myFriendDAO()
    }

    private fun initView() {
        fab_btn = activity?.findViewById(R.id.fabAddFriend)
        listMyFriends = activity?.findViewById(R.id.listMyFriends)

        fab_btn?.setOnClickListener {
            (activity as MainActivity).tampilMyFriendsAddFragment()
        }
        ambilDataTeman()

    }

    private fun tampilTeman() {

        LinearLayoutManager(activity).also { listMyFriends?.layoutManager = it }
        listMyFriends?.adapter = MyFriendAdapter(
            requireActivity(),
            listTeman as ArrayList<MyFriend>
        )
    }

    private fun ambilDataTeman() {
        listTeman = ArrayList()
        myFriendDAO?.ambilSemuaTeman()?.observe(requireActivity()) { r ->
            listTeman = r.toMutableList()
            when {
                listTeman.isEmpty() -> {
                    Toast.makeText(requireActivity(), "Belum Ada Data", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    tampilTeman()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        fab_btn = null
        listMyFriends = null

    }

    companion object {
        fun newInstance(): MyFriendsFragment {
            return MyFriendsFragment()
        }
    }
}