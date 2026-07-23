# AWS Load Balancer & Fixed IP Deployment Guide

This Terraform configuration sets up an AWS Application Load Balancer (ALB) for HTTPS and links it to **AWS Global Accelerator** to provide two static (fixed) public IP addresses.

## 1. Prerequisites
1. **Domain Name:** You must own a domain name (e.g. `api.scriptscout.com`) pointing to this service.
2. **ACM SSL Certificate:** In your AWS Console, navigate to **AWS Certificate Manager (ACM)**, request a public certificate for your domain name (e.g. `*.scriptscout.com` or `api.scriptscout.com`), complete validation, and copy its **ARN**.
3. **AWS CLI & Terraform:** Installed and configured with your IAM credentials.

---

## 2. Configuration
Create a file named `terraform.tfvars` in the `terraform/` directory containing your variables:

```hcl
aws_region           = "us-east-1"
domain_name          = "api.scriptscout.com"
acm_certificate_arn  = "arn:aws:acm:us-east-1:123456789012:certificate/xxxx-xxxx-xxxx"
gemini_api_key       = "your_gemini_api_key_here"
```

---

## 3. Deploying the Resources
Initialize and apply the Terraform configuration:

```bash
terraform init
terraform apply
```

Once deployment completes, Terraform will output:
1. `alb_dns_name`: The canonical DNS target of your Application Load Balancer.
2. `fixed_ip_addresses`: **Two static, fixed public IP addresses** (provided by AWS Global Accelerator).

---

## 4. Configuring Your DNS (Fixed IP and Static Base URL)
In your DNS provider (e.g., Route 53, GoDaddy, Cloudflare, etc.):
1. Create a new **A Record** (e.g., `api.scriptscout.com`).
2. Point it to the **two static IP addresses** outputted by the Terraform run under `fixed_ip_addresses`.
3. Your final base URL will be:
   👉 **`https://api.scriptscout.com`**
