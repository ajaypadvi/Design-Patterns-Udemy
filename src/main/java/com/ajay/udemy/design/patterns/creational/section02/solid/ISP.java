package com.ajay.udemy.design.patterns.creational.section02.solid;

class Document {
}

interface Machine {
    void print(Document d);

    void fax(Document d) throws Exception;

    void scan(Document d) throws Exception;
}

// ok if you need a multifunction machine
class MultiFunctionPrinter implements Machine {
    public void print(Document d) {
        //
    }

    public void fax(Document d) {
        //
    }

    public void scan(Document d) {
        //
    }
}

class OldFashionedPrinter implements Machine {
    public void print(Document d) {
        // yep
    }

    public void fax(Document d) throws Exception {
        throw new Exception("Not Implemented!!");
    }

    public void scan(Document d) throws Exception {
        throw new Exception("Not Implemented!!");
    }
}

interface IPrinter {
    void Print(Document d) throws Exception;
}

interface IScanner {
    void Scan(Document d) throws Exception;
}

class JustAPrinter implements IPrinter {
    public void Print(Document d) {

    }
}

class Photocopier implements IPrinter, IScanner {
    public void Print(Document d) throws Exception {
        throw new Exception();
    }

    public void Scan(Document d) throws Exception {
        throw new Exception();
    }
}

interface MultiFunctionDevice extends IPrinter, IScanner //
{

}

class MultiFunctionMachine implements MultiFunctionDevice {
    // compose this out of several modules
    private IPrinter IPrinter;
    private IScanner scanner;

    public MultiFunctionMachine(IPrinter IPrinter, IScanner scanner) {
        this.IPrinter = IPrinter;
        this.scanner = scanner;
    }

    public void Print(Document d) throws Exception {
        IPrinter.Print(d);
    }

    public void Scan(Document d) throws Exception {
        scanner.Scan(d);
    }
}