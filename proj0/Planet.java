
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

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
        this.imgFileName = p.imgFileName;
        this.mass = p.mass;
        this.xxPos = p.xxPos;
        this.xxVel = p.xxVel;
        this.yyPos = p.yyPos;
        this.yyVel = p.yyVel;
    }

    public double calcDistance(Planet a) {
        double r2 = Math.pow(this.xxPos - a.xxPos, 2) + Math.pow(this.yyPos - a.yyPos, 2);
        double dis = Math.pow(r2, 1.0 / 2);
        return dis;
    }

    public double calcForceExertedBy(Planet a) {
        double dis = this.calcDistance(a);
        double f = G * this.mass * a.mass / (dis * dis);
        return f;
    }

    public double calcForceExertedByX(Planet a) {
        double f = calcForceExertedBy(a);
        double fx = f * (this.xxPos - a.xxPos) / calcDistance(a);
        if (a.xxPos > this.xxPos && fx < 0)
            fx = fx * -1;
        if (a.xxPos < this.xxPos && fx > 0)
            fx = fx * -1;
        return fx;
    }

    public double calcForceExertedByY(Planet a) {
        double f = calcForceExertedBy(a) / calcDistance(a);
        double fy = f * (this.yyPos - a.yyPos);
        if (a.yyPos > this.yyPos && fy < 0)
            fy = fy * -1;
        if (a.yyPos < this.yyPos && fy > 0)
            fy = fy * -1;
        return fy;
    }

    public double calcNetForceExertedByX(Planet[] all) {
        double netf = 0;
        for (int i = 0; i < all.length; i++) {
            if (this.equals(all[i]))
                continue;
            double fx = calcForceExertedByX(all[i]);
            netf += fx;
        }
        return netf;
    }

    public double calcNetForceExertedByY(Planet[] all) {
        double netf = 0;
        for (int i = 0; i < all.length; i++) {
            if (this.equals(all[i]))
                continue;
            double fy = calcForceExertedByY(all[i]);
            netf += fy;
        }
        return netf;
    }

    public void update(double time, double fx, double fy) {
        double ax = fx / this.mass;
        double ay = fy / this.mass;
        double vx = this.xxVel + ax * time;
        double vy = this.yyVel + ay * time;
        double px = this.xxPos + vx * time;
        double py = this.yyPos + vy * time;
        this.xxPos = px;
        this.yyPos = py;
        this.xxVel = vx;
        this.yyVel = vy;
    }

    public void draw() {
        double px = this.xxPos;
        double py = this.yyPos;
        String img = this.imgFileName;
        StdDraw.picture(px, py, "images/" + img);
    }
}
