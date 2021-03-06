package com.example.android.geoquiz;

/**
 * Created by Alex on 16.08.2016.
 */
public class TrueFalse {

    private int mQuestion;

    private boolean usedHelp = false;

    private boolean mTrueQuestion;

    public TrueFalse(int question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public boolean isUsedHelp() {
        return usedHelp;
    }

    public void setUsedHelp(boolean usedHelp) {
        this.usedHelp = usedHelp;
    }

    public int getQuestion() {
        return mQuestion;
    }

    public void setQuestion(int mQuestion) {
        this.mQuestion = mQuestion;
    }

    public boolean isTrueQuestion() {
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean mTrueQuestion) {
        this.mTrueQuestion = mTrueQuestion;
    }
}
