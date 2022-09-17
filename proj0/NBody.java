
public class NBody {

    public static double readRadius(String path) {
        In in = new In(path);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int num = in.readInt();
        in.readDouble();
        Planet[] all = new Planet[num];
        for (int i = 0; i < num; i++) {
            double px = in.readDouble();
            double py = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double mass = in.readDouble();
            String img = in.readString();
            Planet temp = new Planet(px, py, vx, vy, mass, img);
            all[i] = temp;
        }
        return all;
    }

    public static void main(String[] args) {

        double T, dt;
        String temp;
        temp = args[0]; // 是否自动转换
        T = Double.parseDouble(temp);
        temp = args[1];
        dt = Double.parseDouble(temp);
        String filename = args[2];
        Planet[] all = readPlanets(filename);
        double radius = readRadius(filename);

        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet planet : all) {
            planet.draw();
        }
        StdDraw.show();
        double time = 0;
        for (time = 0; time < T; time += dt) {

            double[] xForces = new double[2000];
            double[] yForces = new double[2000];
            for (int i = 0; i < all.length; i++) {
                xForces[i] = all[i].calcNetForceExertedByX(all);
                yForces[i] = all[i].calcNetForceExertedByY(all);
            }
            for (int i = 0; i < all.length; i++) {
                all[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet planet : all) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", all.length);
        StdOut.printf("%.2e\n", radius);
        for (Planet planet : all) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }

    }
}
