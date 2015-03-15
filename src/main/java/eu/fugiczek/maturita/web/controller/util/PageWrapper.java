package eu.fugiczek.maturita.web.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PageWrapper<T> {

	public static final int NUM_OF_DISPLAYED_PAGES = 5;

	private Page<T> page;
	private String url;
	private List<PageItem> items;

	private int currentNumber;

	public PageWrapper(Page<T> page, String url) {
		this.page = page;
		this.url = url;
		items = new ArrayList<>();

		currentNumber = page.getNumber() + 1;

		int start, size;
		if (page.getTotalPages() <= NUM_OF_DISPLAYED_PAGES) {
			start = 1;
			size = page.getTotalPages();
		} else {
			if (currentNumber <= NUM_OF_DISPLAYED_PAGES - NUM_OF_DISPLAYED_PAGES / 2) {
				start = 1;
			} else if (currentNumber >= page.getTotalPages() - NUM_OF_DISPLAYED_PAGES / 2) {
				start = page.getTotalPages() - NUM_OF_DISPLAYED_PAGES + 1;
			} else {
				start = currentNumber - NUM_OF_DISPLAYED_PAGES / 2;
			}
			size = NUM_OF_DISPLAYED_PAGES;
		}

		for (int i = 0; i < size; i++) {
			items.add(new PageItem(start + i, (start + i) == currentNumber));
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<PageItem> getItems(){
        return items;
    }

    public int getNumber(){
        return currentNumber;
    }

    public List<T> getContent(){
        return page.getContent();
    }

    public int getSize(){
        return page.getSize();
    }

    public int getTotalPages(){
        return page.getTotalPages();
    }

    public boolean hasPreviousPage(){
        return page.hasPrevious();
    }

    public boolean hasNextPage(){
        return page.hasNext();
    }

	public class PageItem {

		private int number;
		private boolean current;

		public PageItem(int number, boolean current) {
			this.number = number;
			this.current = current;
		}

		public int getNumber() {
			return number;
		}

		public boolean isCurrent() {
			return current;
		}
	}

}
