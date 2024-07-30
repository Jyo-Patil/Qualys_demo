terraform {
  backend "s3" {
    bucket = "Qualys_backend"
    key    = "terraform.tfstate"
    region = "us-east-1"
    encrypt = true
    dynamodb_table = "Qualys_backend"
    }
  }

