import org.gradle.plugins.ide.eclipse.model.EclipseModel

val eclipseCompileOnly =
    configurations.create("eclipseCompileOnly") {
        extendsFrom(configurations.getByName("compileOnly"))
        isCanBeResolved = true
        isCanBeConsumed = false
    }

configure<EclipseModel> {
    project {
        name = "${rootProject.name}-Plugin"
    }
    classpath {
        plusConfigurations.add(eclipseCompileOnly)
    }
}
