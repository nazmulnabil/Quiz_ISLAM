package com.example.quizislam;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "MyAwesomeQuiz.db";
    private static final int DATABASE_VERSION = 3;

    private SQLiteDatabase db;


    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    private void fillQuestionsTable() {
        Question q1 = new Question("What do we call the Angels who write down what we do ?", "Israfil", " KiramanKatibin", "Mikail", 2);
        addQuestion(q1);
        Question q2 = new Question("Who were considered as the closest companions of the Prophet Muhammad (PBUH)?", "Abu Bakr Al-Siddiq and Omar Bin Al-Khatab", "Abdullah Ibn Masood and Abdullah Ibn Umar", "Khalid Ibn Al-Waleed and Salman Al-Faresy", 1);
        addQuestion(q2);
        Question q3 = new Question("What is the call for prayer called?", "Takbeer", "Salah ", "Adhan", 3);
        addQuestion(q3);
        Question q4 = new Question("Which direction do Muslims face while offering salah (prayers)?", "South", "West", "Kabah (Makkah) ", 3);
        addQuestion(q4);
        Question q5 = new Question("Are women allowed to go to mosques to offer prayers?", "No", "Yes, women can offer prayers in mosques, provided there are separate facilities and provision ", "Women can only listen to prayers ", 2);

        addQuestion(q5);

        Question q6 = new Question("Which Pillar of Islam is Sawm (Fasting)?", "4th", "1st", "3rd ", 1);

        addQuestion(q6);

        Question q7 = new Question("Which night is specified in the Quran as a night greater than 1,000 months?", "Night of Barat (Shab-e-Barat) ", "First night of the month of Ramadhan", "Night of Qadr (Laylat al-Qadr)  ", 3);

        addQuestion(q7);
        Question q8 = new Question("How many Companions of the Prophet (pbuh) were promised Jannah?", "7", "9", "10 ", 3);

        addQuestion(q8);
        Question q9 = new Question("Who is the first martyr in Islam?", "Sumayyah", "Ayesha", "Khadeejah", 1);

        addQuestion(q9);

        Question q10 = new Question("What does the Quran state about the mountains and their reason for existence?", "The Quran states that mountains are just high rises", "The Quran states that mountains have been created for beautification alone", "he Quran states that mountains act like stakes or tent pegs that hold the earth’s crust and give it stability ", 3);

        addQuestion(q10);


        Question q11 = new Question("Which prayer does Allah mention in the Holy Quran to especially guard?", "Fajr Salah (pre-dawn prayers)", "Asar Salah (middle prayers)", "Maghrib prayers (sunset prayers) ", 2);

        addQuestion(q11);

        Question q12 = new Question("What did the Prophet Muhammad (PBUH) mention about the raising of daughters?", "Anyone who raises two daughters with love and affection will enter paradise", "There is no mention about the raising of daughters by the Prophet (PBUH)", "None of the above ", 1);

        addQuestion(q12);
        Question q13= new Question("Does Islam permit women to inherit the property or a share of the property from their parents?", "Only men can inherit the property of the parents and close relatives", "Yes, both men and women are entitled to a specified share of the property left behind by their parents or close relatives", "The property left behind is offered in charity to the poor. ", 2);

        addQuestion(q13);
        Question q14 = new Question("What is the pre-dawn meal known?", "Ifthar", "Suhoor", "None of the above ", 2);

        addQuestion(q14);
        Question q15 = new Question("Whom did Prophet Muhammad (PBUH) describe as a strong person?", "mentally strong", "One who is physically strong ", "One who controls his anger", 3);
        addQuestion(q15);
        Question q16 = new Question("Hadith Qudsi are?", "Words of prophet MUHAMMAD(SAW)", "Words of ALLAH as described by prophet MUHAMMAD (SAW) ", "None", 2);
        addQuestion(q16);
        Question q17 = new Question("Which Prophet Addressed the ruler Nimrod?", "YUSUF(AW)", "LUT(AW) ", "IBRAHIM(AW)", 3);
        addQuestion(q17);
        Question q18 = new Question("The Quran was revealed over how many years ?", "21", "23 ", "22", 2);
        addQuestion(q18);
        Question q19 = new Question("What is the name of the graveyard near Masjid Nabawi that has continously been used since the time of the Prophet, Sall-Allahu alayhi wa sallam??", "Jannatul Mualla", " Jannatul Baqie ", " Karbala", 2);
        addQuestion(q19);
        Question q20 = new Question("What is the name of the Hadith collection compiled by Imam Malik??", "Muwatta", " Bukhari ", "Muslim", 1);
        addQuestion(q20);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
    }

    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }
}
