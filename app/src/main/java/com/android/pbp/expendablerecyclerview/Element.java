package com.android.pbp.expendablerecyclerview;

public class Element {
    public static final String NO_PARENT = "A";
    private String id;
        private String title;
        private String parentId;
        private int level;
        private boolean isExpended;
        private boolean hasChild;

        public Element(String id,String title,String parentId,int level,boolean isExtended,boolean hasChild){
            this.id= id;
            this.title =title;
            this.parentId =parentId;
            this.level = level;
            this.isExpended = isExtended;
            this.hasChild = hasChild;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getParentId() {
            return parentId;
        }

        public void setParentId(String parentId) {
            this.parentId = parentId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public boolean isExtended() {
            return isExpended;
        }

        public void setExtended(boolean expended) {
            isExpended = expended;
        }

        public boolean isHasChild() {
            return hasChild;
        }

        public void setHasChild(boolean hasChild) {
            this.hasChild = hasChild;
        }
    }