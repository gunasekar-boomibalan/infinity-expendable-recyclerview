package com.android.pbp.expendablerecyclerview;

public class Element {
        private String id;
        private String title;
        private String parentId;
        private int level;
        private boolean isExtended;
        private boolean hasChild;

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
            return isExtended;
        }

        public void setExtended(boolean extended) {
            isExtended = extended;
        }

        public boolean isHasChild() {
            return hasChild;
        }

        public void setHasChild(boolean hasChild) {
            this.hasChild = hasChild;
        }
    }