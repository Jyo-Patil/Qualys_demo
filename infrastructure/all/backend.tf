terraform {
  backend "s3" {
    bucket = "ecsbackendbucket"
    key    = "terraform.tfstate"
    region = "us-east-1"
    encrypt = true
    dynamodb_table = "ecstable"
    }
  }

