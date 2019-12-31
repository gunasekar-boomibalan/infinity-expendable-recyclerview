
# Infinity Expendable RecyclerView

This is sample application for **Infinity expendable recyclerview**. this helps to create infinity level of child layout in single **RecyclerView**.

## Sample
![alt text](https://raw.githubusercontent.com/gunasekar-boomibalan/infinity-expendable-recyclerview/master/sample/sample.gif)
![alt text](https://raw.githubusercontent.com/gunasekar-boomibalan/infinity-expendable-recyclerview/master/sample/sample-image.png)

## Usage
The items to parse the list should be the  `Element`  Type. the Element must has  `id`, `parentId`,  `level`, `isExpended`, `hasChild` values

``` java
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
```
## About

Infinity Expendable Recyclerview is maintained by [@Gunasekar-Boomibalan](https://github.com/gunasekar-boomibalan)
