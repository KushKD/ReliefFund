package cmreliefdund.kushkumardhawan.com.relieffund.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import cmreliefdund.kushkumardhawan.com.instructions.MaterialTutorialActivity;
import cmreliefdund.kushkumardhawan.com.instructions.TutorialItem;
import cmreliefdund.kushkumardhawan.com.relieffund.R;

public class Help extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;
    private Button  back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        back = (Button)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Help.this.finish();
            }
        });

        loadTutorial();
    }





    public void loadTutorial() {
        Intent mainAct = new Intent(this, MaterialTutorialActivity.class);
        mainAct.putParcelableArrayListExtra(MaterialTutorialActivity.MATERIAL_TUTORIAL_ARG_TUTORIAL_ITEMS, getTutorialItems(this));
        startActivityForResult(mainAct, REQUEST_CODE);

    }

    private ArrayList<TutorialItem> getTutorialItems(Context context) {
        TutorialItem tutorialItem1 = new TutorialItem("Health", "Life and death are in the hands of God but to help the needy during illness is our moral responsibility. In my state there should not be any case where due to lack of fund the treatment of the sick person could not be done. I appeal to the citizens to donate for the cause generously so that the needy people can be helped. ",
                R.color.g_dark_blue, R.drawable.slide1);

        TutorialItem tutorialItem2 = new TutorialItem("Education", "Education Content...",
                R.color.slide_1,  R.drawable.slide2);

        TutorialItem tutorialItem3 = new TutorialItem("Disaster", "Disaster Content...",
                R.color.slide_4, R.drawable.slide3);

        TutorialItem tutorialItem4 = new TutorialItem("Needy", "Needy Content...",
                R.color.slide_3, R.drawable.slide4);

        TutorialItem tutorialItem5 = new TutorialItem("Girl Child", "Girl Child Content ....",
                R.color.slide_3, R.drawable.splash_back);

        ArrayList<TutorialItem> tutorialItems = new ArrayList<>();
        tutorialItems.add(tutorialItem1);
        tutorialItems.add(tutorialItem2);
        tutorialItems.add(tutorialItem3);
        tutorialItems.add(tutorialItem4);
        tutorialItems.add(tutorialItem5);

        return tutorialItems;
    }
}
