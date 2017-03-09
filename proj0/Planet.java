/**
 * File: Planet.java
 * Author: kyle
 * Date: 3/7/17
 *
 * Defines a class for objects of type Planet
 */

public class Planet {

    public double xxPos, yyPos, xxVel, yyVel, mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {

        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /* Method to calculate cartesian from calling planet to param planet
     * @param target Planet
     * @return double distance
     */
    public double calcDistance(Planet target) {

        double dx, dy, r;
        dx = target.xxPos - this.xxPos;
        dy = target.yyPos - this.yyPos;

        r = Math.sqrt((dx * dx) + (dy * dy));
        return r;
    }

    /* Method to calculate Force exerted on calling planet by param planet
     * @param target Planet
     * @return double force in Newtons
     */
    public double calcForceExertedBy(Planet target) {

        final double GRAV_CONST = (0.0000000000667); // 6.67E^(-10) N-m^2/kg
        double force, r;

        r = this.calcDistance(target);
        force = GRAV_CONST * ((this.mass * target.mass) / (r * r));
        return force;
    }

    /* Method to calculate Force exerted in x direction on calling planet by
     *  parameter planer
     * @param target Planet
     * @return double force in x direction
     */
    public double calcForceExertedByX(Planet target) {

        double r, dx, forceTotal, forceX;

        dx = target.xxPos - this.xxPos;
        r = this.calcDistance(target);
        forceTotal = this.calcForceExertedBy(target);


        forceX = forceTotal * (dx / r);
        return forceX;
    }

    /* Method to calculate Force exerted in y direction on calling planet by
     *  parameter planer
     * @param target Planet
     * @return double force in y direction
     */
    public double calcForceExertedByY(Planet target) {

        double r, dy, forceTotal, forceY;

        dy = target.yyPos - this.yyPos;
        r = this.calcDistance(target);
        forceTotal = this.calcForceExertedBy(target);

        forceY = forceTotal * (dy / r);
        return forceY;
    }

    /* Method calculates NET Force exerted in x direction on calling planet
     *  by planets in parameter Planet array
     * @param planets Planet[] of planets that exert a force
     * @return double NET force in x direction
     */
    public double calcNetForceExertedByX(Planet[] planets) {

        double netForceX = 0;

        for (Planet p : planets) {
            if (!p.equals(this)) {
                netForceX += this.calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    /* Method calculates NET Force exerted in y direction on calling planet
     *  by planets in parameter Planet array
     * @param planets Planet[] of planets that exert a force
     * @return double NET force in y direction
     */
    public double calcNetForceExertedByY(Planet[] planets) {

        double netForceY = 0;

        for (Planet p : planets) {
            if(!p.equals(this)) {
                netForceY += this.calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    /* Method updates a planets velocity and position based on the net X and
     *  Y forces acting upon it, and the duration of the force
     * @param dt duration of time
     * @parap fX net force x direction
     * @param fY net force y direction
     */
    public void update(double dt, double fX, double fY) {

        double aX, aY;

        aX = fX / this.mass;
        aY = fY / this.mass;

        // Update the planets velocity in x and y directions
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;

        // Update the planets new position
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    /* Routine to utilize the StdDraw library to draw a planet on the canvas
     * The planet is drawn according to its initial position
     */
    public void draw() {
        String fileLocation = "./images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, fileLocation);
    }

    @Override
    public String toString() {
        return "Planet's file: " + imgFileName;
    }

}