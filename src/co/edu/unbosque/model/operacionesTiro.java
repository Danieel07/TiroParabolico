package co.edu.unbosque.model;

public class operacionesTiro {
    // Constantes para el movimiento parabólico
    private static final double gravedad = 9.81; // gravedad

    private double velocidad = 60; // velocidad inicial ajustable
    private double angulo = 45; // ángulo en grados (inicialmente 45°)
    private double tiempo = 0;

    private double projectileVelocityX;
    private double projectileVelocityY;

    public double getGravedad() {
        return gravedad;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getAngulo() {
        return angulo;
    }

    public void setAngulo(double angulo) {
        this.angulo = angulo;
        updateVelocities();
    }

    private void updateVelocities() {
        double radianAngle = Math.toRadians(angulo);
        projectileVelocityX = velocidad * Math.cos(radianAngle);
        projectileVelocityY = velocidad * Math.sin(radianAngle);
    }

    public double getProjectileVelocityX() {
        return projectileVelocityX;
    }

    public double getProjectileVelocityY() {
        return projectileVelocityY;
    }

    public void resetTiempo() {
        tiempo = 0;
    }

    public void incrementTiempo(double deltaTime) {
        tiempo += deltaTime;
    }

    public double getTiempo() {
        return tiempo;
    }
}

