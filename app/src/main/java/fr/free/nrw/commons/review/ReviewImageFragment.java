package fr.free.nrw.commons.review;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import fr.free.nrw.commons.R;
import fr.free.nrw.commons.Utils;
import fr.free.nrw.commons.di.CommonsDaggerSupportFragment;

/**
 * Created by root on 19.05.2018.
 */

public class ReviewImageFragment extends CommonsDaggerSupportFragment {

        public static final int SPAM = 0;
        public static final int COPYRIGHT = 1;
        public static final int CATEGORY = 2;

        private int position;
        private String fileName;
        private String catString;
        private View catsView;
        private SimpleDraweeView simpleDraweeView;
        public ProgressBar progressBar;

        public void update(int position, String fileName) {
            this.position = position;
            this.fileName = fileName;

            if (simpleDraweeView!=null) {
                simpleDraweeView.setImageURI(Utils.makeThumbBaseUrl(fileName));
                progressBar.setVisibility(View.GONE);
            }
        }

        public void updateCategories(Iterable<String> categories) {
            catString = TextUtils.join(", ", categories);
            if (catsView != null) {
                ((TextView) catsView).setText(catString);
            }

        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            position = getArguments().getInt("position");
            View layoutView = inflater.inflate(R.layout.fragment_review_image, container,
                    false);
            progressBar = layoutView.findViewById(R.id.progressBar);
            View textView = layoutView.findViewById(R.id.reviewQuestion);
            View textViewQuestion = layoutView.findViewById(R.id.reviewQuestion);
            View textViewQuestionContext = layoutView.findViewById(R.id.reviewQuestionContext);
            catsView = layoutView.findViewById(R.id.reviewCategories);
            String question;
            switch(position) {
                case COPYRIGHT:
                    question = getString(R.string.review_copyright);
                    break;
                case CATEGORY:
                    textViewQuestion.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 0.35f));
                    textViewQuestionContext.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT, 0.35f));
                    question = getString(R.string.review_category);
                    catsView.setVisibility(View.VISIBLE);
                    break;
                case SPAM:
                    question = getString(R.string.review_spam);
                    break;
                default:
                    question = "How did we get here?";
            }
            ((TextView) textView).setText(question);
            simpleDraweeView = layoutView.findViewById(R.id.imageView);

            if (fileName != null) {
                simpleDraweeView.setImageURI(Utils.makeThumbBaseUrl(fileName));
                progressBar.setVisibility(View.GONE);
            }
            if (catString != null) {
                ((TextView) catsView).setText(catString);
            }
            return layoutView;
        }
}
