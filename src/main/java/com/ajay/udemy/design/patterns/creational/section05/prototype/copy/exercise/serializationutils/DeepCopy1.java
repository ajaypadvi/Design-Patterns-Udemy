package com.ajay.udemy.design.patterns.creational.section05.prototype.copy.exercise.serializationutils;

import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

/**
 * Deep copying using SerializationUtils
 */
class Point implements Serializable {
    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "{Point=(x=" + x + ",y=" + y + ")}";
    }
}

class Line implements Serializable {
    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Line deepCopy() {
        Line line = SerializationUtils.roundtrip(this);
        return line;
    }

    @Override
    public String toString() {
        return "{Line=(start=" + start + ",end=" + end + ")}";
    }
}

public class DeepCopy1 {
    public static void main(String[] args) {
        Point first = new Point(1, 2);
        Point second = new Point(8, 9);

        Line line = new Line(first, second);
        Line line2 = line.deepCopy();

        System.out.println(line);
        System.out.println(line2);
    }
}

