terraform {
  backend "s3" {
    bucket = "qualysbackend"
    region = "us-east-1"
    key = "terraform.tfstate"
    }
  }

