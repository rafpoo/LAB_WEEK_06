package com.example.lab_week_06

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lab_week_06.model.CatBreed
import com.example.lab_week_06.model.CatModel
import com.example.lab_week_06.model.Gender

class MainActivity : AppCompatActivity() {
    private val recyclerView : RecyclerView by lazy {
        findViewById(R.id.recycler_view)
    }

    private val catAdapter by lazy {
        CatAdapter(layoutInflater, GlideImageLoader(this), object: CatAdapter.OnClickListener {
            override fun onItemClick(cat: CatModel) {
                showSelectionDialog(cat)
            }
        })
    }


    private val initialCats = listOf(CatModel(
        Gender.Male,
        CatBreed.BalineseJavanese,
        "Fred",
        "Silent and Deadly",
        "https://cdn2.thecatapi.com/images/7dj.jpg"
    ),
        CatModel(
            Gender.Female,
            CatBreed.ExoticShortHair,
            "Wilma",
            "Cuddly Assassin",
            "https://cdn2.thecatapi.com/images/egv.jpg"
        ),
        CatModel(
            Gender.Unknown,
            CatBreed.AmericanCurl,
            "Curious George",
            "Award Winning Investigator",
            "https://cdn2.thecatapi.com/images/bar.jpg"
        ))
    private val classicCats = listOf(
        CatModel(Gender.Male, CatBreed.BalineseJavanese, "Fred", "Silent and Deadly", "https://cdn2.thecatapi.com/images/7dj.jpg"),
        CatModel(Gender.Female, CatBreed.ExoticShortHair, "Wilma", "Cuddly Assassin", "https://cdn2.thecatapi.com/images/egv.jpg"),
        CatModel(Gender.Unknown, CatBreed.AmericanCurl, "Curious George", "Award Winning Investigator", "https://cdn2.thecatapi.com/images/bar.jpg")
    )

    private val elegantCats = listOf(
        CatModel(Gender.Female, CatBreed.OrientalShorthair, "Cleopatra", "Queen of the couch", "https://cdn2.thecatapi.com/images/5sU22_O-2.jpg"),
        CatModel(Gender.Male, CatBreed.Siamese, "Ramses", "Sunbathing expert", "https://cdn2.thecatapi.com/images/ai6JpsStP.jpg")
    )

    private val fluffyCats = listOf(
        CatModel(Gender.Male, CatBreed.Persian, "Sir Fluffington", "Requires constant adoration", "https://cdn2.thecatapi.com/images/8g.jpg"),
        CatModel(Gender.Female, CatBreed.Himalayan, "Snowbelle", "Master of the silent treatment", "https://cdn2.thecatapi.com/images/I_3-4tA60.jpg"),
        CatModel(Gender.Male, CatBreed.Siberian, "Boris", "Loves poetry and long naps", "https://cdn2.thecatapi.com/images/3r3.jpg")
    )

    private val uniqueLookingCats = listOf(
        CatModel(Gender.Female, CatBreed.Sphynx, "Noodles", "Wrinkly and wonderful", "https://cdn2.thecatapi.com/images/7M2m2P4iF.jpg"),
        CatModel(Gender.Male, CatBreed.ScottishFold, "Barnaby", "Ears folded for aerodynamics", "https://cdn2.thecatapi.com/images/y4.jpg")
    )

    private val wildLookingCats = listOf(
        CatModel(Gender.Male, CatBreed.Bengal, "Hunter", "Leopard of the living room", "https://cdn2.thecatapi.com/images/i2V2a-22-.jpg"),
        CatModel(Gender.Female, CatBreed.Abyssinian, "Zola", "Graceful and energetic", "https://cdn2.thecatapi.com/images/yFhASx2b1.jpg"),
        CatModel(Gender.Male, CatBreed.Savannah, "Simba", "King of all he surveys", "https://cdn2.thecatapi.com/images/5g6.jpg")
    )

    private val gentleGiants = listOf(
        CatModel(Gender.Male, CatBreed.MaineCoon, "Moose", "Big, fluffy, and friendly", "https://cdn2.thecatapi.com/images/3j4.jpg"),
        CatModel(Gender.Female, CatBreed.Ragdoll, "Princess Floof", "Goes limp when held", "https://cdn2.thecatapi.com/images/l3.jpg")
    )

    private val sleekCats = listOf(
        CatModel(Gender.Female, CatBreed.RussianBlue, "Misty", "Shy but sweet", "https://cdn2.thecatapi.com/images/a5.jpg"),
        CatModel(Gender.Male, CatBreed.Bombay, "Panther", "A shadow with eyes", "https://cdn2.thecatapi.com/images/i3n1M3TS2.jpg")
    )

    private val playfulCats = listOf(
        CatModel(Gender.Male, CatBreed.JapaneseBobtail, "Bouncer", "Loves chasing laser dots", "https://cdn2.thecatapi.com/images/9o4.jpg"),
        CatModel(Gender.Female, CatBreed.CornishRex, "Rexie", "Curious and adventurous", "https://cdn2.thecatapi.com/images/5p8.jpg")
    )

    private val mysteryCats = listOf(
        CatModel(Gender.Unknown, CatBreed.Singapura, "Pip", "Small cat, big personality", "https://cdn2.thecatapi.com/images/k7g.jpg"),
        CatModel(Gender.Unknown, CatBreed.TurkishAngora, "Ghost", "Appears only for food", "https://cdn2.thecatapi.com/images/a5s.jpg")
    )

    private val allLists = listOf(initialCats, classicCats, elegantCats, fluffyCats, uniqueLookingCats, wildLookingCats, gentleGiants, sleekCats, playfulCats, mysteryCats, elegantCats)

    private var currentListIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val itemTouchHelper = ItemTouchHelper(catAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        catAdapter.setData(
            allLists[currentListIndex]
        )

        val changeListButton = findViewById<Button>(R.id.change_list_button)
        changeListButton.setOnClickListener {
            cycleThroughLists()
        }
    }

    private fun cycleThroughLists() {
        currentListIndex = (currentListIndex + 1) % allLists.size
        catAdapter.setData(allLists[currentListIndex])
    }

    private fun showSelectionDialog(cat: CatModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat selected")
            .setMessage("You have selected cat ${cat.name}")
            .setPositiveButton("OK") { _, _ -> }.show()
    }
}