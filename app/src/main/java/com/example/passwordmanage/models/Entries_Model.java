package com.example.passwordmanage.models;


import java.util.Comparator;

public class Entries_Model {
    private int id;
    private String name;
    private String word;
    private String tag;
    private String pre_Word;

    public Entries_Model() {
    }

    public Entries_Model(int id, String name, String word, String tag, String pre_Word) {
        this.id = id;
        this.name = name;
        this.word = word;
        this.tag = tag;
        this.pre_Word = pre_Word;
    }

    public static Comparator<Entries_Model> EntriesAZcomparator = new Comparator<Entries_Model>() {
        @Override
        public int compare(Entries_Model e1, Entries_Model e2) {
            return e1.getTag().compareToIgnoreCase(e2.getTag());
        }
    };

    public static Comparator<Entries_Model> EntriesZAcomparator = new Comparator<Entries_Model>() {
        @Override
        public int compare(Entries_Model e1, Entries_Model e2) {
            return e2.getTag().compareToIgnoreCase(e1.getTag());
        }
    };

    public static Comparator<Entries_Model> EntriesAscendIDcomparator = new Comparator<Entries_Model>() {
        @Override
        public int compare(Entries_Model e1, Entries_Model e2) {

            return e1.getId() - e2.getId();
        }
    };
    public static Comparator<Entries_Model> EntriesDescendIDcomparator = new Comparator<Entries_Model>() {
        @Override
        public int compare(Entries_Model e1, Entries_Model e2) {
            return e2.getId() - e1.getId();
        }
    };


    @Override
    public String toString() {
        return "Entries_Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", word='" + word + '\'' +
                ", tag='" + tag + '\'' +
                ", pre_Word=" + pre_Word +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPre_Word() {
        return pre_Word;
    }

    public void setPre_Word(String pre_Word) {
        this.pre_Word = pre_Word;
    }
}
