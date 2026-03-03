BEGIN;


CREATE TABLE IF NOT EXISTS public.branch
(
    name character varying,
    PRIMARY KEY (name)
);

CREATE TABLE IF NOT EXISTS public.student
(
    roll_no character varying,
    name character varying NOT NULL,
    branch character varying,
    PRIMARY KEY (roll_no)
);

CREATE TABLE IF NOT EXISTS public.course
(
    name character varying NOT NULL,
    id integer,
    start_date date,
    branch character varying,
    CONSTRAINT "CourseIdentifier" PRIMARY KEY (id, start_date)
);

CREATE TABLE IF NOT EXISTS public.working_day
(
    date date,
    PRIMARY KEY (date)
);

CREATE TABLE IF NOT EXISTS public.off_day
(
    date date,
    reason_of_dayoff character varying,
    PRIMARY KEY (date)
);

CREATE TABLE IF NOT EXISTS public.student_course
(
    roll_no character varying,
    course_id integer,
    start_date date,
    course_name character varying,
    PRIMARY KEY (roll_no, course_id, start_date)
);

CREATE TABLE IF NOT EXISTS public.attendence
(
    date date,
    roll_no character varying,
    course_id integer,
    start_date date,
    reason_of_absence character varying,
    PRIMARY KEY (date, roll_no, course_id, start_date)
);

ALTER TABLE IF EXISTS public.student
    ADD CONSTRAINT "Branch" FOREIGN KEY (branch)
    REFERENCES public.branch (name) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.course
    ADD CONSTRAINT "Branch" FOREIGN KEY (branch)
    REFERENCES public.branch (name) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.student_course
    ADD CONSTRAINT "Student" FOREIGN KEY (roll_no)
    REFERENCES public.student (roll_no) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.student_course
    ADD CONSTRAINT "Course" FOREIGN KEY (course_id, start_date)
    REFERENCES public.course (id, start_date) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.attendence
    ADD CONSTRAINT "Day" FOREIGN KEY (date)
    REFERENCES public.working_day (date) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.attendence
    ADD CONSTRAINT "Student" FOREIGN KEY (roll_no)
    REFERENCES public.student_course (roll_no) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;


ALTER TABLE IF EXISTS public.attendence
    ADD CONSTRAINT "Course" FOREIGN KEY (course_id, start_date)
    REFERENCES public.student_course (course_id, start_date) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    NOT VALID;

END;
