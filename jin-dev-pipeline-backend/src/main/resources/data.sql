DROP TABLE IF EXISTS build;

CREATE TABLE build (
    id INT AUTO_INCREMENT PRIMARY KEY,
    app_name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    build_tool VARCHAR(100) NOT NULL,
    git_address VARCHAR(300) NOT NULL,
    target_server VARCHAR(100) NOT NULL,
    latest_build_number INT,
    latest_build_result VARCHAR(100)
);

INSERT INTO build (id, app_name, description, build_tool, git_address,
target_server, latest_build_number, latest_build_result) VALUES
(1, 'jpetstore', 'sample application(jpetstore)', 'maven'
    ,'https://github.com/mybatis/jpetstore-6.git', 'aws', 0, null);