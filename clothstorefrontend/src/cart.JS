import Stack from '@mui/material/Stack';
import Button from '@mui/material/Button';

export default function BasicButtons() {
  return (
    <Stack spacing={2} direction="row">
      <Button variant="text">ADD TO CART</Button>
      <Button variant="contained">DELETE </Button>
      <Button variant="outlined">QUENTITY</Button>
    </Stack>
  );
}S
