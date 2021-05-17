package com.nam.note;

import android.provider.BaseColumns;

public class NoteContract {
    private NoteContract(){}
    public static class TitleTable implements BaseColumns {
        public static final String ID="id_title";
        public static final String TABLE_NAME="title_table";
        public static final String COLUMN_TITLE="title";

    }
}
