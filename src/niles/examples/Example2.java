package niles.examples;

import org.joml.Vector2f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import niles.lwjgl.entity.Entity;
import niles.lwjgl.entity.Geometry;
import niles.lwjgl.entity.Vertex;
import niles.lwjgl.line.LineEntity;
import niles.lwjgl.loop.Game;
import niles.lwjgl.loop.Scene;
import niles.lwjgl.npsl.MeshShader;
import niles.lwjgl.npsl.PostProcessingShader;
import niles.lwjgl.npsl.Shader;
import niles.lwjgl.rendering.Renderer;
import niles.lwjgl.util.Texture;

public class Example2 extends Game {
	
	public Example2() {
		super(1200, 700, false);
	}
	
	public static void main(String[] args) {
		new Example2();
	}
	
	Shader shader;
	Shader postProcessing;
	
	
	
	@Override
	public void init() {
		postProcessing = new PostProcessingShader("postShader2.glsl");
		shader = new MeshShader("test.glsl");
		
		addScene(new Scene(getWindow()) {
			
			@Override
			public void onload() {
				LineEntity lines = new LineEntity(20);
				lines.addLine(0, 0, 0, 2, 3, 3);
				lines.addLine(2, 3, 3, 5, 0, 3);
				lines.updateVertices();
				
				
				getCamera().setPosition(new Vector3f(0, 0, 10));
				
				Entity e = Entity.cube(0, -2, 0, 8, new Vector3f(1), shader);
				
				e.addTexture(new Texture("res/rock.jpg"));
				
				Entity e2 = Entity.cube(0, -15, 0, 8, new Vector3f(1), shader);
				e2.addTexture(new Texture("res/wood.jpg"));
				e2.getTransform().setScale(new Vector3f(0.5f));
				
				addEntityToScene(e);
				addEntityToScene(e2);
				
				addLineEntityToScene(lines);
				
				/*Geometry g = Geometry.loadModel("res/cube_flat");
				Entity e = new Entity(0, shader);
				e.addTexture(new Texture("res/wood.jpg"));
				e.setGeometry(g);
				addEntityToScene(e);*/
				
				addLight(new Vector3f(-3,40 ,-4), new Vector3f(1, 0.6f, 0.6f), 60);
				addLight(new Vector3f(8,40 ,-4), new Vector3f(0.6f, 0.6f, 1), 60);
				
			}
			
			@Override
			public void update() {
				simpleCameraRotation(1f);
				simpleCameraMovement(0.03f);
				
				
				//will change renderer to render to an fbo and then render the fbo texture to the screen using using a post processing shader.
				usePostProcessing(postProcessing);
			}
		});
		
		setFpsCap(120);
	}
}