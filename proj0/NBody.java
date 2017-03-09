/*
 * File: NBody.java
 * Author: kyle
 * Date: 3/8/17
 * Purpose: Runs the planet simulation specified by a text file
 */

public class NBody {

    public static double readRadius(String fileName) {

        double radius;
        In reader = new In(fileName);
        reader.readInt();
        radius = reader.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String fileName) {

        In reader = new In(fileName);
        int numPlanets = reader.readInt();
        Planet[] planets = new Planet[numPlanets];

        reader.readDouble();

        for (int i = 0; i < numPlanets; i++) {
            planets[i] = new Planet(reader.readDouble(), reader.readDouble(),
                    reader.readDouble(), reader.readDouble(), reader.readDouble(),
                    reader.readString());
        }
        return planets;
    }


    public static void main(String[] args) {

        if (args.length < 3) {
            System.out.println("Please enter 3 Command line args - T, dt, fileName");
        }

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];

        double UniverseRadius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        int totalPlanets = planets.length;

        StdDraw.setScale(-UniverseRadius, UniverseRadius);
        StdDraw.picture(0, 0, "./images/starfield.jpg");

        for (Planet p : planets) {
            p.draw();
        }


        double time = 0;
        while (time != T) {


            double[] xForces = new double[totalPlanets];
            double[] yForces = new double[totalPlanets];

            for (int i = 0; i < totalPlanets; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < totalPlanets; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "./images/starfield.jpg");

            for (Planet p : planets) {
                p.draw();
            }
            StdDraw.show(20);

            T += dt;


        }
    }
}