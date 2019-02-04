ALTER TABLE public.book_category ADD parent_id int NULL;

ALTER TABLE public.book_category
  ADD CONSTRAINT FK_parent_category
    FOREIGN KEY (parent_id) REFERENCES public.book_category (id);