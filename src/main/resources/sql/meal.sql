create table meal(
	id INT PRIMARY KEY AUTO_INCREMENT,
    name varchar(255),
    category varchar(255),
    drinkalternate varchar(255),
    area varchar(255),
    instructions varchar(1000),
    mealthumb varchar(255),
    urlyoutube varchar(255),
    tags varchar(255),
    ingredient_id INT,
    measure_id INT,
    source varchar(255),
    imagesource varchar(255),
    datemodified varchar(255)
)
